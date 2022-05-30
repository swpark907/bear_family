import React, { useEffect } from "react";
import { PW_REGEX } from "../../constants/regex";
import { Title } from "../common";

const PwInput = ({ validCheck, setValidCheck, userInfo, setUserInfo }) => {
  const pwInputHandler = ({ target }) => {
    setUserInfo({ ...userInfo, pw: target.value });
  };
  const matchPwInputHandler = ({ target }) => {
    setUserInfo({ ...userInfo, matchPw: target.value });
  };

  useEffect(() => {
    const result = PW_REGEX.test(userInfo.pw);
    result
      ? setValidCheck({ ...validCheck, pw: true })
      : setValidCheck({ ...validCheck, pw: false });
  }, [userInfo.pw]);

  useEffect(() => {
    const result = PW_REGEX.test(userInfo.matchPw);
    userInfo.pw === userInfo.matchPw && result
      ? setValidCheck({ ...validCheck, matchPw: true })
      : setValidCheck({ ...validCheck, matchPw: false });
  }, [userInfo.matchPw]);

  return (
    <div className="pw-form">
      <div className="pw">
        <Title variant={"tertiary"}>비밀번호</Title>
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
            className={
              "pw-input__info" +
              (userInfo.pw && !validCheck.pw ? " invalid" : " hide")
            }
            htmlFor="pwInput"
          >
            비밀번호 형식에 맞지 않습니다.
          </label>
        </div>
      </div>
      <div className="pw-match">
        <Title variant={"tertiary"}>비밀번호 확인</Title>
        <div className="pw-match-input">
          <input
            className="pw-match-input__input"
            id="pwMatchInput"
            type="password"
            onChange={matchPwInputHandler}
          />
          <label
            className={
              "pw-match-input__info" +
              (userInfo.matchPw && !validCheck.matchPw ? " invalid" : " hide")
            }
            htmlFor="pwMatchInput"
          >
            비밀번호를 다시 확인해주세요.
          </label>
        </div>
      </div>
    </div>
  );
};

export default PwInput;
