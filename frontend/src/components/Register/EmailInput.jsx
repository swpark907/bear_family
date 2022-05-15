import React from 'react';

const EmailInput = ({setEmail, btnOnClick, email, emailCheck}) => {
  return (
    <div className="email-input">
        <div className="email-input__input-box">
          <input
            type="text"
            className="input-box__input"
            id="emailInput"
            placeholder="인증 수단으로 이용 될 이메일입니다."
            onChange={({ target }) => {
              setEmail(target.value);
            }}
          />

          <button className="input-box__button" onClick={btnOnClick}>
            인증번호 전송
          </button>
        </div>
        <label
          htmlFor="emailInput"
          className={
            "email-input__validation " +
            (email && !emailCheck ? "invalid" : "hide")
          }
        >
          이메일 형식에 맞게 입력해주세요.
        </label>
      </div>
  );
};

export default EmailInput;