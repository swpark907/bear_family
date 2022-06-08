import axios from "axios";

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
    console.log("login 액션 실행");

    const form = new FormData();
    form.append("identity", identity);
    form.append("password", password);

    try {
      const response = await axios.post(`${URL}/login`, {
        data: form,
        headers: {
          "Content-Type": "application/json",
        },
      });
      const accessToken = response.accessToken;
      const userId = response.identity;
      localStorage.setItem("access-token", accessToken);
      localStorage.setItem("userId", userId);

      dispatch({
        type: LOGIN,
        payload: { identity },
      });
    } catch (e) {
      console.log(e);
      dispatch({
        type: LOGIN_ERR,
        payload: e,
      });
    }
    console.log("login 액션 끝");
    // return {
    //   type: LOGIN,
    //   payload: { identity },
    // };
  };

export const logout = () => {
  localStorage.removeItem("access-token");
  localStorage.removeItem("userId");
  return {
    type: LOGOUT,
  };
};

export const getUserInfo = ({ userId }) => {
  const accessToken = localStorage.getItem("access-token");
  const config = {
    headers: {
      accessToken,
    },
  };

  if (!accessToken) {
    // 리프레시 토큰? 다시 요청?
    console.log("존재하는 토큰이 없음");
  }
};

const loginReducer = (state = initialState, action) => {
  switch (action.type) {
    case LOGIN:
      return { ...state, isLogin: true, userInfo: action.payload };
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
