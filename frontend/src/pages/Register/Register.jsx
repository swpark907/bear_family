import React from "react";
import RegisterForm from "../../components/Register/RegisterForm";
import "../../styles/scss/main.scss";
import { Title } from "../../components/common";

const Register = () => {
  return (
    <section className="section-register">
      <Title variant="primary" className="section-register__title">회원가입</Title>
      <RegisterForm />
    </section>
  );
};

export default Register;
