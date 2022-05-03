import React from "react";
import RegisterForm from "../../components/Register/RegisterForm";
import '../../styles/scss/main.scss';


const Register = () => {
  return (
    <section className="section__register">
      <h2>회원가입</h2>
      <RegisterForm />
    </section>
  );
};

export default Register;
