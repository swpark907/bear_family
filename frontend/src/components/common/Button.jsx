import React, { useEffect, useState } from "react";
import "../../styles/scss/main.scss";

const Button = ({ className, variant, children, onClick, disabled }) => {
  const [variantClass, setVariantClass] = useState("");

  useEffect(() => {
    if (
      variant === "primary" ||
      variant === "secondary" ||
      variant === "tertiary"
    ) {
      setVariantClass(variant);
    }
  }, []);

  return (
    <button
      className={(className ? className + " " : "") + variantClass}
      onClick={onClick}
      disabled={disabled}
    >
      {children}
    </button>
  );
};

export default Button;
