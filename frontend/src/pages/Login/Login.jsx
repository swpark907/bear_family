import React from "react";
import { Title, Button } from "../../components/common/index";
import { Link } from "react-router-dom";

const Login = () => {
  return (
    <section className="section-login">
      <div>로고 들어갈 자리</div>
      <Title variant="secondary">로그인</Title>
      <form className="login__form">
        <input type="text" className="login__id-input" />
        <input type="password" className="login__pw-input" />
        <Button variant={"primary"} className="login__button">
          로그인
        </Button>
      </form>
      <p>아직 회원이 아니신가요?</p>{" "}
      <p>
        {" "}
        <Link to="/register">회원가입</Link>
      </p>
    </section>
  );
};

export default Login;
