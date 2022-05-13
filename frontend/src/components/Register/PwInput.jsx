import React, { useEffect, useState } from "react";
import { PW_REGEX } from "../../constants/regex";

const PwInput = ({validCheck, setValidCheck, userInfo, setUserInfo}) => {

  const [pw, setPw] = useState("");
  const [pwCheck, setPwCheck] = useState(false);

  const [matchPw, setMatchPw] = useState("");
  const [matchPwCheck, setMatchPwCheck] = useState(false);

  const pwInputHandler = ({ target }) => {
    setPw(target.value);
  };
  const matchPwInputHandler = ({ target }) => {
    setMatchPw(target.value);
  };

  useEffect(() => {
    const result = PW_REGEX.test(pw);
    result ? setPwCheck(true) : setPwCheck(false);
  }, [pw]);

  useEffect(() => {
    const result = PW_REGEX.test(matchPw);
    matchPw === pw && result ? setMatchPwCheck(true) : setMatchPwCheck(false);
  }, [matchPw, pw]);

  return (
    <div className="pw-form">
      <h3>비밀번호</h3>
      <div className="pw-input">
        <input
          className="pw-input__input"
          id="pwInput"
          type="password"
          onChange={pwInputHandler}
        />
        <label className="pw-input__info" htmlFor="pwInput">
          영문자, 숫자, 특수문자를 꼭 포함하여 8~24자
        </label>
        <label
          className={"pw-input__info " + (pw && !pwCheck ? "invalid" : "hide")}
          htmlFor="pwInput"
        >
          비밀번호 형식에 맞지 않습니다.
        </label>
      </div>
      <h3>비밀번호 확인</h3>
      <div className="pw-match-input">
        <input
          className="pw-match-input__input"
          id="pwMatchInput"
          type="password"
          onChange={matchPwInputHandler}
        />
        <label className={"pw-match-input__info " + (matchPw && !matchPwCheck ? "invalid" : "hide")} htmlFor="pwMatchInput">
          비밀번호와 일치하지 않습니다.
        </label>
      </div>
    </div>
  );
};

export default PwInput;
