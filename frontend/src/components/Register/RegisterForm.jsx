import React from "react";
import TermsForm from "./TermsForm";
import EmailAuth from "./EmailAuth";
import PwInput from "./PwInput";
import IdInput from "./IdInput";
import NickNameInput from "./NickNameInput";

const RegisterForm = ({ ...props }) => {
  return (
    <form className="register__form">
      <TermsForm {...props} />
      <EmailAuth {...props} />
      <IdInput {...props} />
      <NickNameInput {...props} />
      <PwInput {...props} />
    </form>
  );
};

export default RegisterForm;
