import { useState } from "react";
import axios from "axios";

const useRegister = ({ url, userInfo }) => {
  const [response, setResponse] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const userInfoRegist = async () => {
    console.log('실행')
    setLoading(true);
    try {
      const response = await axios.get(url);
      const {data} = response;
      console.log(data);
      setResponse(data.payload);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  return {response, error, loading, userInfoRegist};
};

export default useRegister;
