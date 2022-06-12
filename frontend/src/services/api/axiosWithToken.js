import axios from "axios";

const URL = process.env.REACT_APP_BASE_URL;

const axiosWithToken = async ({ url, data, method }) => {

  const accessToken = localStorage.getItem("access-token");

  const instance = axios.create({
    baseURL: URL,
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${accessToken}`,
    },
  });

  return instance;
};

export default axiosWithToken;
