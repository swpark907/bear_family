import React, { useState } from "react";
import { Button } from "../common";
import useTimer from "../../hooks/useTimer";

const CodeInput = ({
  validCheck,
  errCode,
  errMsg,
  codeInputActivate,
  checkCodeHandler,
  timerState,
}) => {
  
  const { timerMin, timerSec } = useTimer(3, 0, timerState);

  const classChanger = () => {
    switch (errCode) {
      case "hide":
        return "hide";
      case "timeOut" || "invalid":
        return "invalid";
      case "valid":
        return "valid";
      default:
        console.log("classChagner 오류");
        return;
    }
  };

  return (
    <div className={"code-input" + (!codeInputActivate ? " hide" : " active")}>
      <div className="code-input__header">
        <div className="code-input__input-box">
          <input
            type="number"
            className="input-box__input"
            id="codeInput"
            disabled={errCode === "valid" ? true : false}
          />
          <div
            className={
              "input-box__timer" + (validCheck.emailCode ? " hide" : " active")
            }
          >
            {timerMin}:{timerSec}
          </div>
        </div>
        <Button
          className={"code-input__button"}
          variant={"secondary"}
          onClick={checkCodeHandler}
        >
          인증번호 확인
        </Button>
      </div>
      <label
        htmlFor="codeInput"
        className={"code-input__info " + classChanger()}
      >
        {errMsg}
      </label>
    </div>
  );
};

export default CodeInput;
