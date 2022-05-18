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
    reqTerms: false,
    email: false,
    emailCode: false,
    id: false,
    userName: false,
    pw: false,
    matchPw: false,
  });

  const props = { userInfo, setUserInfo, validCheck, setValidCheck };

  const [submitErrMsg, setSubmitErrMsg] = useState("form 제출 에러 메시지");
  const [isSubmitErr, setIsSubmitErr] = useState(false);

  const submitHandler = (e) => {
    e.preventDefault();
    const validCheckToArray = Object.entries(validCheck);
    const item = validCheckToArray.find((element) => element[1] === false);

    if (item) {
      setIsSubmitErr(true);
      switch (item[0]) {
        case "reqTerms":
          setSubmitErrMsg("필수약관에 동의해주세요.");
          return;
        case "email":
          setSubmitErrMsg("이메일을 입력해주세요.");
          return;
        case "emailCode":
          setSubmitErrMsg("이메일 인증을 완료해주세요.");
          return;
        case "id":
          setSubmitErrMsg("아이디를 입력해주세요.");
          return;
        case "userName":
          setSubmitErrMsg("닉네임을 입력해주세요.");
          return;
        case "pw" || "matchPw":
          setSubmitErrMsg("비밀번호를 확인해주세요.");
        default:
          return;
      }
    }
    // 서버에 정보 전달 후 가입 완료 페이지
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
      <TermsForm {...props} />
      <EmailAuth {...props} />
      <IdInput {...props} />
      <NickNameInput {...props} />
      <PwInput {...props} />
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
