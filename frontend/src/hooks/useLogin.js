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
      setError(false);
      navigate("/home");
    } else if(loginStore.isLoginErr === null){
      setError(false);
    } else{
      setError(true);
    }
  }, [loginStore.isLoginErr]);

  const onLogin = async ({ identity, password }) => {
    setIsLoading(true);
    dispatch(login({ identity, password }));
    setIsLoading(false);
  };

  return { isLoading, error, onLogin, setError };
};

export default useLogin;
