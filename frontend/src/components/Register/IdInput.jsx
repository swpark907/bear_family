import React from "react";
import { useState, useEffect } from "react";
import { Title, Button } from "../common";
import { ID_REGEX } from "../../constants/regex.js";
import axios from "axios";
import URL from "../../constants/url";

const IdInput = ({ userInfo, setUserInfo, validCheck, setValidCheck }) => {
  const [validErrMsg, setValidErrMsg] = useState("");
  const [유효성검사, set유효성검사] = useState(false);

  useEffect(() => {
    const result = ID_REGEX.test(userInfo.id);
    result ? set유효성검사(true) : set유효성검사(false);
  }, [userInfo.id]);

  const idInputHandler = ({ target }) => {
    setUserInfo({ ...userInfo, id: target.value });
  };

  const idCheckHandler = async (e) => {
    e.preventDefault();

    if (!유효성검사) {
      setValidErrMsg("아이디 형식에 맞지 않습니다.");
      setValidCheck({ ...validCheck, id: false });
      return;
    }

    // 버튼이 클릭되면 백엔드에 중복확인 한 후 받아온 응답에 따른 클래스명 조정

    const form = new FormData();
    form.append("identity", userInfo.id);
    const { data } = await axios.post(`${URL}/checkId`, form);
    const 중복확인통과 = data.response; // 중복확인 통신 로직 추가

    console.log(중복확인통과);

    if (중복확인통과 === "success") {
      setValidErrMsg("사용 가능한 아이디입니다.");
      setValidCheck({ ...validCheck, id: true });
    } else {
      setValidErrMsg("중복된 아이디입니다.");
      setValidCheck({ ...validCheck, id: false });
    }
  };

  return (
    <div className="id-form">
      <Title variant={"secondary"}>아이디</Title>
      <div className="id-input">
        <input
          type="text"
          className="id-input__input"
          id="idInput"
          placeholder="로그인 시 아이디 설정"
          onChange={idInputHandler}
        />
        <Button
          className={"id-input__id-check-button"}
          variant={"secondary"}
          onClick={idCheckHandler}
        >
          아이디 중복 확인
        </Button>
      </div>
      <label htmlFor="idInput" className="id-form__info">
        6~20자 가능, 특수문자는 사용이 불가합니다.
      </label>
      <label
        htmlFor="idInput"
        className={"id-form__valid" + (validCheck.id ? " valid" : " invalid")}
      >
        {validErrMsg}
      </label>
    </div>
  );
};

export default IdInput;
