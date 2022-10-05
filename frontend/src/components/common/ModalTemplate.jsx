import { useEffect, useState } from "react";
import reactDom from "react-dom";
import "../../styles/scss/main.scss";
import Button from "./Button";

function ModalPortal({ children }) {
  const body = document.getElementById("root");
  return reactDom.createPortal(children, body);
}

function ModalTemplate({ className, btnContent, state, setState, children }) {
  const modalClose = ({ target }) => {
    if (
      !target.closest(".modal-content-box") ||
      target.className.includes("modal-button") 
    ) {
      setState(false);
    }
  };

  return (
    <ModalPortal>
      <div
        className={"modal-container " + (state ? "active" : "hide")}
        onClick={modalClose}
      >
        <div className={"modal-content-box " + (className ? className : "")}>
          <div className="modal-content">{children}</div>
          {btnContent && <Button className="modal-button" variant="secondary">{btnContent}</Button>}
        </div>
      </div>
    </ModalPortal>
  );
}

export default ModalTemplate;
