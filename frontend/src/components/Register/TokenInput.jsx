import React, { useEffect, useState } from "react";
import { Button } from "../common";
import useTimer from "../../hooks/useTimer";

const TokenInput = ({
  validCheck,
  errCode,
  errMsg,
  tokenInputActivate,
  checkTokenHandler,
  timerState,
  setCheckToken,
  errMsgHandler,
  timerMin,
  timerSec,
}) => {
  const [tokenInputClass, setTokenInputClass] = useState("");

  useEffect(() => {
    switch (errCode) {
      case "hide":
        setTokenInputClass("hide");
        return;
      case "timeOut":
        setTokenInputClass("invalid");
        return;
      case "invalid":
        setTokenInputClass("invalid");
        return;
      case "valid":
        setTokenInputClass("valid");
        return;
      default:
        console.log("classChagner 오류");
        return;
    }
  }, [errCode]);

  return (
    <div
      className={"token-input" + (!tokenInputActivate ? " hide" : " active")}
    >
      <div className="token-input__header">
        <div className="token-input__input-box">
          <input
            type="number"
            className="input-box__input"
            id="tokenInput"
            disabled={
              errCode === "valid" || errCode === "timeOut" ? true : false
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
          disabled={validCheck.emailToken ? true : false}
        >
          인증번호 확인
        </Button>
      </div>
      <label
        htmlFor="tokenInput"
        className={"token-input__info " + tokenInputClass}
      >
        {errMsg}
      </label>
    </div>
  );
};

export default TokenInput;
