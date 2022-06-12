import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { login } from "../reducers/loginReducer";

const useLogin = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const loginStore = useSelector((state) => state.loginReducer);

  useEffect(() => {
    if (loginStore.isLoginErr !== null && loginStore.isLoginErr === false) {
      navigate("/home");
    }
  }, [loginStore.isLoginErr]);
  // isLoginErr가 아니라 isLogin이 되어야 되는 것이 아닌가 생각해봐야됨

  const onLogin = async ({ identity, password }) => {
    setIsLoading(true);
    dispatch(login({ identity, password }));
    // action에서 store에 정보는 모두 전달
    setIsLoading(false);
  };

  return { isLoading, error, onLogin };
};

export default useLogin;
