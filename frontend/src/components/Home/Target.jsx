import React from 'react';
import { Button } from '../common';

const RandomInfo = () => {
  return (
    <div className='home__random-info'>
      <p>목표가 및 잔액</p>
      <Button variant={'tertiary'}>더 보러가기</Button>
    </div>
  );
};

export default RandomInfo;