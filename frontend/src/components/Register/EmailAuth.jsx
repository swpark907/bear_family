import axios from "axios";
import React, { useEffect, useState } from "react";
import { EMAIL_REGEX } from "../../constants/regex";
import useTimer from "../../hooks/useTimer";
import { ModalTemplate, Title } from "../common";
import TokenInput from "./TokenInput";
import EmailInput from "./EmailInput";
import URL from "../../constants/url";
import { useDispatch } from "react-redux";
import { loading, unloading } from "../../reducers/loadingReducer";

const EmailAuth = ({ userInfo, setUserInfo, validCheck, setValidCheck }) => {
  const [timerState, setTimerState] = useState(false);
  const [tokenInputActivate, setTokenInputActivate] = useState(false);
  const [errCode, setErrCode] = useState("hide");
  const [tokenErrMsg, setTokenErrMsg] = useState("인증코드 관련 메시지");
  const [sendErrMsg, setSendErrMsg] = useState("번호 전송 관련 에러")
  const [인증번호모달, set인증번호모달] = useState(false);
  const [sendTokenResult, setSendTokenResult] = useState(false);
  const [checkToken, setCheckToken] = useState("");

  const dispatch = useDispatch();

  const tokenErrMsgHandler = (errCode) => {
    setErrCode(errCode);
    switch (errCode) {
      case "timeOut":
        setTokenErrMsg("인증시간이 만료되었습니다.");
        setValidCheck({ ...validCheck, emailToken: false });
        return;
      case "invalid":
        setTokenErrMsg("인증번호가 맞지 않습니다.");
        setValidCheck({ ...validCheck, emailToken: false });
        return;
      case "valid":
        setTokenErrMsg("인증되었습니다.");
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
    if (validCheck.emailToken) {
      // 이미 인증을 받은상태
      return;
    }
    
    if (!validCheck.email) {
      set인증번호모달(true);
      setSendErrMsg("이메일 형식에 맞게 입력해주세요.")
      return;
    }

    const form = new FormData();
    form.append("to", userInfo.email);
    dispatch(loading());
    try {
      
      const response = await axios.post(
        `${URL}/sendEmailauth`,
        form
      );
      
      setSendErrMsg("인증번호가 전송되었습니다.")
      set인증번호모달(true);
      setTokenInputActivate(true);
      setTimerState(true);
      setSendTokenResult(true);
    } catch (e) {
      set인증번호모달(true);
      setTokenInputActivate(false);
      setSendErrMsg("잠시 후 다시 시도해주세요.")
    } finally{
      dispatch(unloading());
    }
  };

  const { timerMin, timerSec, interval } = useTimer(
    3,
    0,
    timerState,
    setTimerState,
    tokenErrMsgHandler
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
      tokenErrMsgHandler("valid");
      setTimerState(false);
      return;
    }

    tokenErrMsgHandler("invalid");
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
        tokenErrMsg={tokenErrMsg}
        tokenInputActivate={tokenInputActivate}
        checkTokenHandler={checkTokenHandler}
        timerState={timerState}
        setCheckToken={setCheckToken}
        timerMin={timerMin}
        timerSec={timerSec}
      />
      <ModalTemplate
        btnContent={"닫기"}
        state={인증번호모달}
        setState={set인증번호모달}
      >
        {sendErrMsg}
      </ModalTemplate>
    </div>
  );
};

export default EmailAuth;
