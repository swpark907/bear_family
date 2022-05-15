import React from "react";
import { useState, useEffect } from "react";
import { Title, Button } from "../common";
import { ID_REGEX } from "../../constants/regex.js";

const IdInput = () => {
  const [validErrMsg, setValidErrMsg] = useState("");
  const [validChecked, setValidChecked] = useState(false);
  const [is사용가능, setIs사용가능] = useState(false);
  const [userId, setUserId] = useState("");
  const [validUserId, setValidUserId] = useState(false);

  useEffect(() => {
    const result = ID_REGEX.test(userId);
    result ? setValidUserId(true) : setValidUserId(false);
    console.log({validUserId, validErrMsg});
  }, [userId]);

  const onClickHandler = (e) => {
    e.preventDefault();
    // 버튼이 클릭되면 백엔드에 중복확인 한 후 받아온 응답에 따른 클래스명 조정

    if(!validUserId){
      setValidErrMsg("아이디 형식에 맞지 않습니다.")
      setIs사용가능(false);
      return;
    }

    const result = true;
    setValidChecked(true);
    if (result) {
      setValidErrMsg("사용 가능한 아이디입니다.");
      setIs사용가능(true);
    } else {
      setValidErrMsg("중복된 아이디입니다.");
      setIs사용가능(false);
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
          onChange={(e) => {
            setUserId(e.target.value);
          }}
        />
        <Button
          className={"id-input__id-check-button"}
          variant={"secondary"}
          onClick={onClickHandler}
        >
          아이디 중복 확인
        </Button>
      </div>
      <label htmlFor="idInput" className="id-form__info">
        6~20자 가능, 특수문자는 사용이 불가합니다.
      </label>
      <label
        htmlFor="idInput"
        className={
          "id-form__valid" +
          (validChecked && is사용가능 ? " valid" : " invalid")
        }
      >
        {validErrMsg}
      </label>
    </div>
  );
};

export default IdInput;
