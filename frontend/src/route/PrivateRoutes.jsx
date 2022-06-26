import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Navigate, Outlet } from "react-router-dom";

const PrivateRoutes = ({ ...props }) => {
  const info = useSelector((state) => state.loginReducer);
  const [isLogin, setIsLogin] = useState(info.isLogin);

  // useEffect(() => {
  //   setIsLogin(info.loginReducer);
  //   console.log(isLogin);
  // }, []);

  return <>{isLogin ? <Outlet /> : <Navigate to="login" />}</>;
};

export default PrivateRoutes;
