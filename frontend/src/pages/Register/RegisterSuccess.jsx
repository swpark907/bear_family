import React from "react";
import { Title, Button } from "../../components/common";
import { useNavigate, Link } from "react-router-dom";

const RegisterSuccess = () => {
  const navigate = useNavigate();
  const buttonOnClick = () => {
    navigate("/login");
  };

  return (
    <section className="section-register-success">
      <Title variant={"primary"} className="register-success__title">
        가입완료
      </Title>
      <div className="register-success__container">
        <Title variant={"primary"} className="container__title">
          회원가입 완료!
        </Title>
        <p>로그인 후에 서비스 이용이 가능합니다.</p>
        <Button onClick={buttonOnClick} className={"container__button"}>
          로그인 페이지로 이동
        </Button>
      </div>
    </section>
  );
};

export default RegisterSuccess;
