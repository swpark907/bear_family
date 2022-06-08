import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { login } from "../reducers/loginReducer";
import axios from "axios";

const useLogin = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const dispatch = useDispatch();
  const loginState = useSelector((state) => state.loginReducer.userInfo); // 삭제예정 코드
  const navigate = useNavigate();

  const onLogin = async (userInfo) => {
    setIsLoading(true);
    dispatch(login({ identity: "test-id-input", password: "testpw" }));
    // action에서 store에 정보는 모두 전달
    setIsLoading(false);
    // navigate("/home");
  };

  return { isLoading, error, onLogin };
};

export default useLogin;
