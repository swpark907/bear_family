import React from "react";

const EmailInput = ({
  userInfo,
  setUserInfo,
  validCheck,
  sendCodeHandler,
}) => {
  return (
    <div className="email-input">
      <div className="email-input__input-box">
        <input
          type="text"
          className="input-box__input"
          id="emailInput"
          placeholder="인증 수단으로 이용 될 이메일입니다."
          onChange={({ target }) => {
            setUserInfo({...userInfo, email: target.value});
          }}
        />

        <button className="input-box__button" onClick={sendCodeHandler}>
          인증번호 전송
        </button>
      </div>
      <label
        htmlFor="emailInput"
        className={
          "email-input__validation " +
          (userInfo.email && !validCheck.email ? "invalid" : "hide")
        }
      >
        이메일 형식에 맞게 입력해주세요.
      </label>
    </div>
  );
};

export default EmailInput;
