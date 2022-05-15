import React, { useEffect, useState } from "react";
import { EMAIL_REGEX } from "../../constants/regex";
import { ModalTemplate, Title } from "../common";
import CodeInput from "./CodeInput";
import EmailInput from "./EmailInput";

const EmailAuth = () => {
  const [emailCheck, setEmailCheck] = useState(false);
  const [timer, setTimer] = useState(false);
  const [email, setEmail] = useState("");
  const [inputActivate, setInputActivate] = useState(false);
  const [errMsg, setErrMsg] = useState("인증시간이 만료되었습니다.");
  const [codeCheck, setCodeCheck] = useState(false);
  const [인증번호모달, set인증번호모달] = useState(false);

  // 인증 시간이 만료되었습니다.
  // 인증번호가 맞지 않습니다.
  // 인증되었습니다. ==> 인증번호 전송 버튼 비활성화
  const errMsgHandler = (sign) => {
    switch (sign) {
      case "tiemOut":
        setErrMsg("인증시간이 만료되었습니다.");
        setCodeCheck(false);
        return;
      case "invalid":
        setErrMsg("인증번호가 맞지 않습니다.");
        setCodeCheck(false);
        return;
      case "valid":
        setErrMsg("인증되었습니다.");
        setCodeCheck(true);
      default:
        throw new Error("인증번호 오류");
        return;
    }
  };

  const timerHandler = () => {};

  const sendCodeHandler = (e) => {
    e.preventDefault();
    set인증번호모달(true);
    
    if (!emailCheck) {
      return;
    }
    setInputActivate(true);
    // 인증번호 전송 요청
    // 요청 완료 후,
    // 인증번호 전송이 완료가 백에서 확인되면 true를 리턴하는 로직 추가
    // setInputActivate(false);
  };

  const checkCodeHandler = (e) => {
    // 사용자에게 입력받은 인증번호 백엔드에 전송
    // 인증번호가 맞다면
    // codCheck를 true로 만든다.
    // 그리고 input을 다시 disable로 만든다
    setCodeCheck(!codeCheck);
  };

  useEffect(() => {
    const result = EMAIL_REGEX.test(email);
    result ? setEmailCheck(true) : setEmailCheck(false);
  }, [email]);

  return (
    <div className="email-form">
      <Title variant="secondary">이메일</Title>
      <EmailInput
        setEmail={setEmail}
        email={email}
        sendCodeHandler={sendCodeHandler}
        emailCheck={emailCheck}
      />
      <CodeInput
        codeCheck={codeCheck}
        errMsg={errMsg}
        inputActivate={inputActivate}
      />
      <ModalTemplate
        btnContent={"닫기"}
        state={인증번호모달}
        setState={set인증번호모달}
      >
        {emailCheck
          ? "인증번호가 전송되었습니다"
          : "이메일 형식에 맞게 다시 입력해주세요."}
      </ModalTemplate>
    </div>
  );
};

export default EmailAuth;
