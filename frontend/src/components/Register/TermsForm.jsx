import React, { useState } from "react";
import RequiredTerms from "./RequiredTerms";
import OptionalTerms from "./OptionalTerms";
import { Title } from "../common";

const TermsForm = () => {

  return (
    <div className="terms-form">
      <Title variant="secondary">약관동의</Title>
      <RequiredTerms></RequiredTerms>
      <OptionalTerms></OptionalTerms>
    </div>
  );
};

export default TermsForm;
