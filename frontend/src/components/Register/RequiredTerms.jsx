import React from "react";
import { useState } from "react";
import { Title } from "../common";
import Terms from "./Terms";

const RequiredTerms = () => {
  const reqTerms = [
    {
      termsTitle: "약관 이름",
      termsContent:
        "약관 내용aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaㅁㄴㅇㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹㄹaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
    },
    {
      termsTitle: "약관 이름",
      termsContent: "약관 내용",
    },
    {
      termsTitle: "약관 이름",
      termsContent: "약관 내용",
    },
    {
      termsTitle: "약관 이름",
      termsContent: "약관 내용",
    },
  ];

  const [checkedTerms, setCheckedTerms] = useState([]);

  return (
    <ul className="terms-form__required">
      <Title variant="tertiary">필수 약관 전체 동의</Title>
      {reqTerms.map((term, key) => (
        <Terms
          term={term}
          key={key}
          type="required"
        />
      ))}
    </ul>
  );
};

export default RequiredTerms;
