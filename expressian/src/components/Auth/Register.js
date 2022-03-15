import React, {useState} from 'react';
import NewUserForm from './NewUserForm';
import Container from '../common/Container';
import LoginForm from './LoginForm';
import Splash from '../common/Splash';
import splashImg from '../../assets/Splash.jpg';
//import RegSplash from "../../assets/RegSplash.jpg";

const Register = (props) => {
   /* const [newUser, setUser] = useState((
        email: '',
        password: '',
        fName: '',
        lName: '',
        isMember: false,
        licenseNumber: ''
    ))

    const updateForm = (field, value) => {
        setNewUser({
            ..newUser,
            [field]: value
        })
    }

    const onSubmit = () => {};
*/

    return (
    <Container>
    <Splash image={splashImg} style={{
    height: '20vh',
    color: '#F1F1F1'
    }}>
        <h1>Register</h1>
        </ Splash>
        <NewUserForm />
        </Container>
    )
}

export default Register;


       // <NewUserForm
           // newUser={newUser}
           // onChange={updateForm}
           // onSubmit={onSubmit}
      //  />