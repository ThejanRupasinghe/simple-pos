import React from 'react';
import {Col, Button, Form, FormGroup, Label, Input, Container} from 'reactstrap';

class LoginForm extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Container>
                <Col md={{size: 6 , offset: 3}}>
                    <Form>
                        <h2>Sign In</h2>
                        <hr/>
                        <FormGroup row>
                            <Label for="username">Username</Label>
                            <Input type="text" name="username" id="username" placeholder="your username" autoFocus required/>
                        </FormGroup>
                        <FormGroup row>
                            <Label for="examplePassword">Password</Label>
                            <Input type="password" name="password" id="username" placeholder="your password" required/>
                        </FormGroup>
                        <FormGroup check row>
                            <Col md={{offset: 5}}>
                                <Button color="primary" size="lg">Login</Button>
                            </Col>
                        </FormGroup>
                    </Form>
                </Col>
            </Container>
        );
    }
}

export default LoginForm;