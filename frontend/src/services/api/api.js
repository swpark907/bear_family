import axios from "axios";

const URL = process.env.REACT_APP_BASE_URL;

export const api = () => {
  const instance = axios.create({
    baseURL: URL,
    headers: {
      "Content-Type": "application/json",
    },
  });
  return instance;
};

// 매 페이지 랜더링마다 실행되는 함수
export const apiWithToken = () => {
  const accessToken = localStorage.getItem("access-token");

  const instance = axios.create({
    baseURL: URL,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${accessToken}`,
    },
  });

  return instance;
};


