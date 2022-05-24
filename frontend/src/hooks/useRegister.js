import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const useRegister = ({ url, userInfo }) => {
  const [response, setResponse] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();

  const userInfoRegist = async () => {
    setLoading(true);
    try {
      const response = await axios.post(url, userInfo);
      const { data } = response;
      console.log(data);
      setResponse(data.payload);
      navigate("/register/success");
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  return { response, error, loading, userInfoRegist };
};

export default useRegister;
