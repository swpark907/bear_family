import React from 'react';
import Terms from './Terms';
import { Title } from '../common';

const OptionalTerms = () => {

  const opTerms = [
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
    {
      termsTitle: "약관 이름",
      termsContent: "약관 내용",
    },
  ]

  

  return (
    <ul className='terms-form__optional'>
      <Title variant="tertiary" >선택 약관  전체 동의</Title>
      {opTerms.map((term, key) => (
        <Terms term={term} key={key} type="optional"/>
      ))}
    </ul>
  );
};

export default OptionalTerms;