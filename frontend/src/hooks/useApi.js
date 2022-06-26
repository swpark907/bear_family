import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { apiWithToken, api } from "../services/api/api";

const useApi = ({ url }) => {
  const [pageData, setPageData] = useState(null);

  const getUserInfo = useSelector((state) => state.userInfoReducer);

  const expiredTime = Date.parse(getUserInfo.tokenExpiredTime);
  const currentTime = Date.now();
  const remainTime = expiredTime - currentTime;

  const getNewToken = async () => {
    const instance = apiWithToken();
    if (remainTime < 10000) {
      const userInfo = {
        refreshToken: getUserInfo.refreshToken,
      };
      const { data } = await instance.post("/refresh", userInfo);
      console.log(data);
      if (data.response === "success") {
        localStorage.setItem("access-token", data.data);
      }
    }
  };

  const getPageData = async () => {
    await getNewToken();
    const instance = apiWithToken();
    const response = await instance.post(url, {
      id: getUserInfo.userInfo.id,
    });
    console.log(response);
    setPageData(response.data); // 콘솔 찍어보고 수정하기
  };

  useEffect(() => {
    getPageData();
  }, []);

  return { pageData };
};

export default useApi;
