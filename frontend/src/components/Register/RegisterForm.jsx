import React from "react";
import Terms from "./Terms";
import EmailAuth from "./EmailAuth";
import IdInput from "./IdInput";
import NameInput from "./NameInput";
import PwInput from "./PwInput";

const RegisterForm = () => {
  return (
    <form>
      <Terms />
      <EmailAuth />
      <IdInput />
      <NameInput />
      <PwInput />
    </form>
  );
};

export default RegisterForm;
