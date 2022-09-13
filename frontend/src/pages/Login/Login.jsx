import React, { useState } from "react";
import { Button } from "../../components/common/index";
import { Link } from "react-router-dom";
import useLogin from "../../hooks/useLogin";
import { useEffect } from "react";
import { useSelector } from "react-redux";
import { useCallback } from "react";

const Login = () => {
  const [userId, setUserId] = useState(null);
  const [userPw, setUserPw] = useState(null);
  const [errMsg, setErrMsg] = useState("");

  const { onLogin, error, setError } = useLogin();

  const onUserIdHandler = ({ target }) => {
    setUserId(target.value);
  };

  const onUserPwHandler = ({ target }) => {
    setUserPw(target.value);
  };

  const onLoginHandler = useCallback((e) => {
    e.preventDefault();
    const userInfo = {
      identity: userId,
      password: userPw,
    };
    onLogin(userInfo);

    // 로그인에 실패 했을 경우
    // 1. 서버에러
    // 2. 아이디, 비밀번호 불일치

    // const result = false;

    // if (!result) {
    //   setIsValid(false);
    //   return;
    // }
  }, [userId, userPw]);

  const getErr = useSelector((state) => state.loginReducer);

  useEffect(() => {
    // 로그인 통신 장애 스테이터스에 따라 errMsg를 변경하는 로직 추가
  }, [getErr]);

  return (
    <section className="section-login">
      <div>로고 들어갈 자리</div>
      <form className="login__form" id="loginForm">
        <input
          type="text"
          className="login__id-input"
          onChange={onUserIdHandler}
        />
        <input
          type="password"
          className="login__pw-input"
          onChange={onUserPwHandler}
        />
        <label
          htmlFor="loginForm"
          className={"login__info" + (error ? " invalid" : " hide")}
        >
          아이디 또는 비밀번호가 맞지 않습니다.
        </label>
        <Button
          variant={"primary"}
          className="login__button"
          onClick={onLoginHandler}
        >
          로그인
        </Button>
      </form>
      <p>
        아직 회원이 아니신가요?<Link to="/register">회원가입</Link>
      </p>
    </section>
  );
};

export default Login;
