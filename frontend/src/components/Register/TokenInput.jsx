import React, { useState } from "react";
import { Button } from "../common";
import useTimer from "../../hooks/useTimer";

const TokenInput = ({
  validCheck,
  errToken,
  errMsg,
  tokenInputActivate,
  checkTokenHandler,
  timerState,
  setCheckToken,
  errMsgHandler,
  timerMin,
  timerSec,
}) => {
  const classChanger = () => {
    switch (errToken) {
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
    <div className={"token-input" + (!tokenInputActivate ? " hide" : " active")}>
      <div className="token-input__header">
        <div className="token-input__input-box">
          <input
            type="number"
            className="input-box__input"
            id="tokenInput"
            disabled={
              errToken === "valid" || errToken === "timeOut" ? true : false
            }
            onChange={({ target }) => setCheckToken(target.value)}
          />
          <div
            className={
              "input-box__timer" + (validCheck.emailToken ? " hide" : " active")
            }
          >
            {timerMin}:{timerSec}
          </div>
        </div>
        <Button
          className={"token-input__button"}
          variant={"secondary"}
          onClick={checkTokenHandler}
        >
          인증번호 확인
        </Button>
      </div>
      <label
        htmlFor="tokenInput"
        className={"token-input__info " + classChanger()}
      >
        {errMsg}
      </label>
    </div>
  );
};

export default TokenInput;
