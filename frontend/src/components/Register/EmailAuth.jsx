import axios from "axios";
import React, { useEffect, useState } from "react";
import { EMAIL_REGEX } from "../../constants/regex";
import useTimer from "../../hooks/useTimer";
import { ModalTemplate, Title } from "../common";
import TokenInput from "./TokenInput";
import EmailInput from "./EmailInput";
import URL from "../../constants/url";

const EmailAuth = ({ userInfo, setUserInfo, validCheck, setValidCheck }) => {
  const [timerState, setTimerState] = useState(false);
  const [tokenInputActivate, setTokenInputActivate] = useState(false);
  const [errCode, setErrCode] = useState("hide");
  const [errMsg, setErrMsg] = useState("인증코드 관련 메시지");
  const [인증번호모달, set인증번호모달] = useState(false);
  const [sendTokenResult, setSendTokenResult] = useState(false);
  const [checkToken, setCheckToken] = useState("");

  const errMsgHandler = (errCode) => {
    setErrCode(errCode);
    switch (errCode) {
      case "timeOut":
        setErrMsg("인증시간이 만료되었습니다.");
        setValidCheck({ ...validCheck, emailToken: false });
        return;
      case "invalid":
        setErrMsg("인증번호가 맞지 않습니다.");
        setValidCheck({ ...validCheck, emailToken: false });
        return;
      case "valid":
        setErrMsg("인증되었습니다.");
        setValidCheck({ ...validCheck, emailToken: true });
        return;
      case "hide":
        return;
      default:
        throw new Error("인증번호 오류");
    }
  };

  const sendTokenHandler = async (e) => {
    e.preventDefault();
    errMsgHandler("hide");
    if (validCheck.emailToken) {
      return;
    }
    set인증번호모달(true);

    if (!validCheck.email) {
      console.log("이메일 오류"); // 오류처리 로직으로 변경
      return;
    }

    const form = new FormData();
    form.append("to", userInfo.email);
    try {
      setTokenInputActivate(true);
      setTimerState(true);
      setSendTokenResult(true);
      const response = await axios.post(
        `${URL}/sendEmailauth`,
        form
      );
    } catch (e) {
      setTokenInputActivate(false);
      console.log(e);
    }
  };

  const { timerMin, timerSec, interval } = useTimer(
    3,
    0,
    timerState,
    setTimerState,
    errMsgHandler
  );

  const checkTokenHandler = async (e) => {
    e.preventDefault();
    const formdata = { email: userInfo.email, token: checkToken };
    const response = await axios({
      url: `${URL}/checkEmailauth`,
      method: "post",
      data: JSON.stringify(formdata),
      headers: {
        "Content-Type": "application/json",
      },
    });

    const result = response.data.data;

    if (result === true) {
      setValidCheck({ ...validCheck, emailToken: true });
      errMsgHandler("valid");
      setTimerState(false);
      return;
    }

    errMsgHandler("invalid");
  };

  useEffect(() => {
    const result = EMAIL_REGEX.test(userInfo.email);
    result
      ? setValidCheck({ ...validCheck, email: true })
      : setValidCheck({ ...validCheck, email: false, emailToken: false });
  }, [userInfo.email]);

  return (
    <div className="email-form">
      <Title variant="secondary">이메일</Title>
      <EmailInput
        setUserInfo={setUserInfo}
        userInfo={userInfo}
        validCheck={validCheck}
        sendTokenHandler={sendTokenHandler}
        tokenInputActivate={tokenInputActivate}
        errCode={errCode}
      />
      <TokenInput
        validCheck={validCheck}
        errCode={errCode}
        errMsg={errMsg}
        tokenInputActivate={tokenInputActivate}
        checkTokenHandler={checkTokenHandler}
        timerState={timerState}
        setCheckToken={setCheckToken}
        errMsgHandler={errMsgHandler}
        timerMin={timerMin}
        timerSec={timerSec}
      />
      <ModalTemplate
        btnContent={"닫기"}
        state={인증번호모달}
        setState={set인증번호모달}
      >
        {sendTokenResult
          ? "인증번호가 전송되었습니다"
          : "인증번호 전송이 실패했습니다. 잠시 후에 다시 시도해주세요."}
      </ModalTemplate>
    </div>
  );
};

export default EmailAuth;
