import { combineReducers } from "redux";
import { loginReducer, userInfoReducer } from "./loginReducer";
import { loadingReducer } from "./loadingReducer";
import { persistReducer } from "redux-persist";
import storage from "redux-persist/lib/storage";

const persistConfig = {
  key: "root",
  // localStorage에 저장합니다.
  storage,
  // auth, board, studio 3개의 reducer 중에 auth reducer만 localstorage에 저장합니다.
  whitelist: ["loginReducer"],
  // blacklist -> 그것만 제외합니다
};

const rootReducer = combineReducers({
  loadingReducer,
  loginReducer,
  userInfoReducer,
});

export default persistReducer(persistConfig, rootReducer);
