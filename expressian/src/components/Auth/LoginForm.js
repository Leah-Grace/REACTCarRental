import React from 'react';
import Container from '../common/Container';
import Form from '../common/Form';
import InlineInputContainer from '../common/InlineInputContainer';
import Input from '../common/Input';
import Button from '../common/Button';


const LoginForm = (props) => {
    const {newLogin} = props;

    const handleChange = (e) => {
        props.onChange(e.target.id, e.target.value);
    }


    return (
    <Container>
        <Form onSubmit={props.onSubmit} style={{marginTop: '1em'}} >
            <InlineInputContainer>
                <Input
                    name="username"
                    id="username"
                    value={newLogin.username}
                    placeholder={"Email"}
                    onChange={handleChange}
                    type="email"
                    required
                />
            </ InlineInputContainer>
            <InlineInputContainer>
                <Input
                    name="password"
                    id="password"
                    value={newLogin.password}
                    placeholder="Password"
                    onChange={handleChange}
                    type="password"
                    required
                />
            </ InlineInputContainer>
            <Button>Submit</Button>
        </ Form>
        </ Container>
    )
}

export default LoginForm;