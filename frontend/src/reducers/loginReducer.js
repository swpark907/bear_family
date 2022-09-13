import axios from "axios";
import { api } from "../services/api/api";

const URL = process.env.REACT_APP_BASE_URL;

const LOGIN = "AUTH/LOGIN";
const LOGOUT = "AUTH/LOGOUT";
const LOGIN_ERR = "AUTH/LOGIN_ERR";
const LOGIN_SERVER_ERR = "AUTH/LOGIN_SERVER_ERR";

const loginInitState = {
  isLogin: false,
  isLoginErr: null,
  isLoginServerErr: null,
  errMsg: null,
};

const userInfoInitState = {
  userInfo: null,
  refreshToken: null,
  tokenExpiredTime: null,
};

export const login =
  ({ identity, password }) =>
  async (dispatch) => {
    try {
      const instance = api();
      const { data } = await instance.post("/login", { identity, password });

      if (data.response === "success") {
        const accessToken = data.data.accessToken;
        const refreshToken = data.data.refreshToken;
        const tokenExpiredTime = data.data.accessTokenExpiredTime;
        const userInfo = data.data.user;

        localStorage.setItem("access-token", accessToken);

        dispatch({
          type: LOGIN,
          payload: { userInfo, refreshToken, tokenExpiredTime },
        });
      } else if (data.response === "fail") {
        dispatch({
          type: LOGIN_ERR,
          payload: data.message,
        });
      }
    } catch (e) {
      console.log("로그인 에러 발생", e);
      dispatch({
        type: LOGIN_SERVER_ERR,
        payload: e,
      });
    }
  };

export const logout = () => {
  localStorage.removeItem("access-token");
  localStorage.removeItem("persist:root");  
  return {
    type: LOGOUT,
  };
};

export const loginReducer = (state = loginInitState, action) => {
  switch (action.type) {
    case LOGIN:
      return {
        ...state,
        isLogin: true,
        isLoginErr: false,
      };
    case LOGOUT:
      return {
        ...state,
        isLogin: false,
        isLoginErr: false,
      };
    case LOGIN_ERR:
      return {
        ...state,
        isLogin: false,
        isLoginErr: true,
        errMsg: action.payload,
      };
    case LOGIN_SERVER_ERR:
      return {
        ...state,
        isLogin: false,
        isLoginServerErr: true,
        errMsg: action.payload,
      };
    default:
      return state;
  }
};

export const userInfoReducer = (state = userInfoInitState, action) => {
  switch (action.type) {
    case LOGIN:
      const payload = action.payload;
      return {
        ...state,
        userInfo: payload.userInfo,
        refreshToken: payload.refreshToken,
        tokenExpiredTime: payload.tokenExpiredTime,
      };
    case LOGOUT:
      return {
        ...state,
        userInfo: null,
        refreshToken: null,
        tokenExpiredTime: null,
      };
    default:
      return state;
  }
};
