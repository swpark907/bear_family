import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { loading, unloading } from "../reducers/loadingReducer";

const URL = process.env.REACT_APP_BASE_URL;

const useRegister = ({ url, userInfo }) => {
  const [response, setResponse] = useState(null);
  const [error, setError] = useState(null);

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const userInfoRegist = async () => {
    dispatch(loading())
    try {
      const response = await axios.post(`${URL}/${url}`, userInfo);
      const { data } = response;
      setResponse(data.payload);
      navigate("/register/success");
    } catch (err) {
      setError(err);
    } finally {
      dispatch(unloading());
    }
  };

  return { response, error, loading, userInfoRegist };
};

export default useRegister;
