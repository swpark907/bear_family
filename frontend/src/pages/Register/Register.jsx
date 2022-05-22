import React, { useState } from "react";
import RegisterForm from "../../components/Register/RegisterForm";
import "../../styles/scss/main.scss";
import { Title, Button, ModalTemplate } from "../../components/common";
import useRegister from "../../hooks/useRegister";


const Register = () => {
  const [submitErrMsg, setSubmitErrMsg] = useState("form 제출 에러 메시지");
  const [isSubmitErr, setIsSubmitErr] = useState(false);

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

  const {userInfoRegist} = useRegister({
    url: "http://146.56.185.52/regist",
    userInfo: {
      identity: userInfo.id,
      password: userInfo.pw,
      name: userInfo.userName,
      email: userInfo.email,
    },
  });

  const submitHandler = (e) => {
    e.preventDefault();
    const validCheckToArray = Object.entries(validCheck);
    const item = validCheckToArray.find((element) => element[1] === false);
    console.log("1");
    if (item) {
      switch (item[0]) {
        case "reqTerms":
          setIsSubmitErr(true);
          setSubmitErrMsg("필수약관에 동의해주세요.");
          return;
        case "email":
          setIsSubmitErr(true);
          setSubmitErrMsg("이메일을 입력해주세요.");
          return;
        case "emailCode":
          setIsSubmitErr(true);
          setSubmitErrMsg("이메일 인증을 완료해주세요.");
          return;
        case "id":
          setIsSubmitErr(true);
          setSubmitErrMsg("아이디를 입력해주세요.");
          return;
        case "userName":
          setIsSubmitErr(true);
          setSubmitErrMsg("닉네임을 입력해주세요.");
          return;
        case "pw" || "matchPw":
          setIsSubmitErr(true);
          setSubmitErrMsg("비밀번호를 확인해주세요.");
          return;
        default:
          break;
      }
    }
    userInfoRegist(); 
  };

  const props = { userInfo, setUserInfo, validCheck, setValidCheck };

  return (
    <section className="section-register">
      <Title variant="primary" className="section-register__title">
        회원가입
      </Title>
      <button
        onClick={(e) => {
          e.preventDefault();
          console.log(validCheck, userInfo);
        }}
      >
        state check in console
      </button>
      <RegisterForm {...props} />
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
    </section>
  );
};

export default Register;
