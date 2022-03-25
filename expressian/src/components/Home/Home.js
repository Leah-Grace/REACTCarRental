import React from 'react';
import Container from '../common/Container';
import Splash from '../common/Splash';
import splashImg from '../../assets/Splash.jpg';
import Button from '../common/Button';
import FaButton from '../faCommon/FaButton';

const Home = () => {
  return (
    <Container style={{backgroundColor: "#ffffff"}}>
    <Splash
        image={splashImg} style={{
            height: '30vh',
            color: "#c9426d"
            }}>
         <h1 style={{textShadow: '1px 1px #6f2960'}}>Welcome to Expressian Car Rental!</h1>
         <h2 style={{textShadow: '1px 1px #6f2960'}}>All cars have coffee holders</h2>
        </Splash>

    </Container>
  )
}

export default Home;