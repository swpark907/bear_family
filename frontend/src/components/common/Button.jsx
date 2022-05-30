import React from "react";
import "../../styles/scss/main.scss";

const Button = ({ className, variant, children, onClick, disabled }) => {
  return (
    <button
      className={(className ? className + " " : "") + variant}
      onClick={onClick}
      disabled={disabled}
    >
      {children}
    </button>
  );
};

export default Button;
