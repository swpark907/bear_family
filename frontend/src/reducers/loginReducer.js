import axios from "axios";
import { api } from "../services/api/api";

const URL = process.env.REACT_APP_BASE_URL;

const LOGIN = "AUTH/LOGIN";
const LOGOUT = "AUTH/LOGOUT";
const LOGIN_ERR = "AUTH/LOGIN_ERR";

const initialState = {
  isLogin: false,
  isLoginErr: null,
  errMsg: null,
  userInfo: null,
};

export const login =
  ({ identity, password }) =>
  async (dispatch) => {
    try {
      const instance = api();
      const { data } = await instance.post("/login", { identity, password });
      console.log(data);

      if (data.response === "success") {
        const accessToken = data.data.accessToken;
        const refreshToken = data.data.refreshToken;
        const tokenExpiredTime = data.data.accessTokenExpiredTime;
        const userInfo = data.data.user;
        console.log(accessToken);

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
      console.log(e);
      dispatch({
        type: LOGIN_ERR,
        payload: e,
      });
    }
  };

export const logout = () => {
  localStorage.removeItem("access-token");
  localStorage.removeItem("userId");
  return {
    type: LOGOUT,
  };
};

const loginReducer = (state = initialState, action) => {
  switch (action.type) {
    case LOGIN:
      const payload = action.payload;
      return {
        ...state,
        isLogin: true,
        isLoginErr: false,
        userInfo: payload.userInfo,
        refreshToken: payload.refreshToken,
        tokenExpiredTime: payload.tokenExpiredTime,
      };
    case LOGOUT:
      return {
        ...state,
        isLogin: false,
        isLoginErr: false,
        userInfo: null,
      };
    case LOGIN_ERR:
      return {
        ...state,
        isLogin: false,
        isLoginErr: true,
        errMsg: action.payload,
      };
    default:
      return state;
  }
};

export default loginReducer;
