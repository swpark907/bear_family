import React from "react";
import { Button } from "../common";

const CodeInput = ({ validCheck, errMsg, inputActivate }) => {
  return (
    <div className={"code-input" + (!inputActivate ? " hide" : " active")}>
      <div className="code-input__header">
        <div className="code-input__input-box">
          <input
            type="number"
            className="input-box__input"
            id="codeInput"
          />
          <div className="input-box__timer">03:00</div>
        </div>
        <Button
          className={"code-input__button"}
          variant={"secondary"}
        >
          인증번호 확인
        </Button>
      </div>
      <label
        htmlFor="codeInput"
        className={
          "code-input__info " + (!validCheck.emailCode ? "invalid" : "valid")
        }
      >
        {errMsg}
      </label>
    </div>
  );
};

export default CodeInput;
