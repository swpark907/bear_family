import React, { useEffect, useState } from "react";
import { EMAIL_REGEX } from "../../constants/regex";
import { ModalTemplate, Title } from "../common";
import CodeInput from "./CodeInput";
import EmailInput from "./EmailInput";

const EmailAuth = () => {
  const [emailCheck, setEmailCheck] = useState(false);
  const [email, setEmail] = useState("");
  const [errMsg, setErrMsg] = useState("인증시간이 만료되었습니다.");
  const [codeCheck, setCodeCheck] = useState(false);
  const [인증번호모달, set인증번호모달] = useState(false);

  const btnOnClick = (e) => {
    e.preventDefault();
    set인증번호모달(true);
    timerHandler();
    setCodeCheck(!codeCheck); // 인증번호 전송이 완료되면 true 리턴 로직
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
        btnOnClick={btnOnClick}
        emailCheck={emailCheck}
      />
      <CodeInput codeCheck={codeCheck} errMsg={errMsg} />
      <ModalTemplate
        btnContent={"닫기"}
        state={인증번호모달}
        setState={set인증번호모달}
      >
        인증번호가 전송되었습니다.
      </ModalTemplate>
    </div>
  );
};

export default EmailAuth;
