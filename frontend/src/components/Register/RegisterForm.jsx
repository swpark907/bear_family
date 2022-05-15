import React, { useEffect } from "react";
// import { TermsForm, EmailAuth, PwInput, IdInput, NickNameInput } from "./index";
import TermsForm from "./TermsForm";
import EmailAuth from "./EmailAuth";
import PwInput from "./PwInput";
import IdInput from "./IdInput";
import NickNameInput from "./NickNameInput";
import { useState } from "react";
import { Button, ModalTemplate } from "../common";

const RegisterForm = () => {
  const [userInfo, setUserInfo] = useState({
    email: "",
    id: "",
    userName: "",
    pw: "",
    matchPw: "",
  });

  const [validCheck, setValidCheck] = useState({
    email: false,
    emailCode: false,
    id: false,
    userName: false,
    pw: false,
    matchPw: false,
  });
  const [submitErrMsg, setSubmitErrMsg] = useState("form 제출 에러 메시지");
  const [isSubmitErr, setIsSubmitErr] = useState(false);

  const submitHandler = (e) => {
    e.preventDefault();
    // validCheck 위에서부터 차례대로 false인 부분에 관한 에러메시지
    setIsSubmitErr(true);
  };

  return (
    <form className="register__form">
      <button
        onClick={(e) => {
          e.preventDefault();
          console.log(validCheck);
        }}
      >
        state check in console
      </button>
      <TermsForm
        validCheck={validCheck}
        setValidCheck={setValidCheck}
        userInfo={userInfo}
        setUserInfo={setUserInfo}
      />
      <EmailAuth
        validCheck={validCheck}
        setValidCheck={setValidCheck}
        userInfo={userInfo}
        setUserInfo={setUserInfo}
      />
      <IdInput
        validCheck={validCheck}
        setValidCheck={setValidCheck}
        userInfo={userInfo}
        setUserInfo={setUserInfo}
      />
      <NickNameInput
        validCheck={validCheck}
        setValidCheck={setValidCheck}
        userInfo={userInfo}
        setUserInfo={setUserInfo}
      />
      <PwInput
        validCheck={validCheck}
        setValidCheck={setValidCheck}
        userInfo={userInfo}
        setUserInfo={setUserInfo}
      />
      <Button
        className="form-submit-button"
        variant="primary"
        onClick={submitHandler}
      >
        가입하기
      </Button>
      <ModalTemplate
        className="register-submit-err-modal"
        btnContent="닫기"
        state={isSubmitErr}
        setState={setIsSubmitErr}
      >
        {submitErrMsg}
      </ModalTemplate>
    </form>
  );
};

export default RegisterForm;
