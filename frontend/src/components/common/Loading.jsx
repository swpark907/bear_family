import React, {useEffect} from "react";
import reactDom from "react-dom";

function LoadingPortal({ children }) {
  const body = document.getElementById("root");
  return reactDom.createPortal(children, body);
}

const Loading = ({ state }) => {

  return (
    <LoadingPortal>
      <div className={"loading-container" + (state ? " active": " hide")}>
        <h1 className="loading__inner">로딩중..</h1>
      </div>
    </LoadingPortal>
  );
};

export default Loading;
