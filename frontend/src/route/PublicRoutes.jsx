import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import { Outlet, Navigate } from "react-router-dom";

const PublicRoutes = ({ restricted }) => {
  const info = useSelector((state) => state.loginReducer);

  const [isLogin, setIsLogin] = useState(info.isLogin);

  return <>{isLogin && restricted ? <Navigate to="home" /> : <Outlet />}</>;
};

export default PublicRoutes;
