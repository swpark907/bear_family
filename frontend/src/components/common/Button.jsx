import React from "react";
import "../../styles/scss/main.scss";

const Button = ({ className, variant, children, onClick }) => {
  return (
    <button
      className={(className ? className + " " : "") + variant}
      onClick={onClick}
    >
      {children}
    </button>
  );
};

export default Button;
