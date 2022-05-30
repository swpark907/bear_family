import React, { useState, useEffect } from "react";
import RequiredTerms from "./RequiredTerms";
import OptionalTerms from "./OptionalTerms";
import { Title } from "../common";

const TermsForm = ({ ...props }) => {

  const [reqAllCheck, setReqAllCheck] = useState(false);
  const [opAllCheck, setOpAllCheck] = useState(false);

  // 약관은 서버에서 받아올 것
  const reqTerms = [
    {
      id: 0,
      title: "필수약관 이름",
      content: "약관 내용",
    },
    {
      id: 1,
      title: "약관 이름",
      content: "약관 내용",
    },
    {
      id: 2,
      title: "약관 이름",
      content: "약관 내용",
    },
    {
      id: 3,
      title: "약관 이름",
      content: "약관 내용",
    },
  ];

  const opTerms = [
    {
      id: 4,
      title: "선택약관 이름",
      content: "약관 내용",
    },
    {
      id: 5,
      title: "약관 이름",
      content: "약관 내용",
    },
    {
      id: 6,
      title: "약관 이름",
      content: "약관 내용",
    },
    {
      id: 7,
      title: "약관 이름",
      content: "약관 내용",
    },
  ];

  return (
    <div className="terms-form">
      <Title variant="secondary">약관동의</Title>
      <RequiredTerms reqTerms={reqTerms} reqAllCheck={reqAllCheck} {...props} />
      <OptionalTerms opTerms={opTerms} opAllCheck={opAllCheck} {...props} />
    </div>
  );
};

export default TermsForm;
