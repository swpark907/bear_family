import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { apiWithToken, api } from "../services/api/api";
import { logout } from "../reducers/loginReducer";

const useApi = ({ url, method }) => {
  const [pageData, setPageData] = useState(null);

  const dispatch = useDispatch();

  const getUserInfo = useSelector((state) => state.userInfoReducer);

  const expiredTime = Date.parse(getUserInfo.tokenExpiredTime);
  const currentTime = Date.now();
  const remainTime = expiredTime - currentTime;

  
  // 이 부분이 매 페이지 랜더링 될 때 실행되어야함.
  // 요청 method가 뭔지 받아서 쓸것인가(switch)?
  const getNewToken = async () => {
    const instance = apiWithToken();

    console.log(expiredTime, currentTime);
    if (remainTime && remainTime < 10000) {
      const userInfo = {
        refreshToken: getUserInfo.refreshToken,
      };
      try {
        const { data } = await instance.post("/refresh", userInfo);
        console.log(userInfo);
        if (data.response === "success") {
          localStorage.setItem("access-token", data.data);
          console.log("새로운 토큰 발행");
        }        
      } catch (e) {
        dispatch(logout);
      }
    } else {
      console.log("아직 만료되지 않은 토큰입니다.");
    }
  };

  // 처음 랜더링 시 로그인이 자동으로 되어야함.
  // 로그인이 되어야 하는 조건
  // 리프레시토큰, 엑세스토큰을 통해 userInfo가 store에 담겨야 함.

  const getPageData = async () => {
    await getNewToken();
    const instance = apiWithToken();
    const response = await instance.get(url);
    console.log(response);
    setPageData(response.data); // 콘솔 찍어보고 수정하기
  };

  useEffect(() => {
    getPageData();
  }, []);

  return { pageData };
};

export default useApi;
