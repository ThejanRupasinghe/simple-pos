import React from 'react';
import {Table, Container, Button} from "reactstrap";

class OrderTable extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            orders: [
                {id: 1, date: "Date1", total: "$50"},
                {id: 2, date: "Date2", total: "$80"},
                {id: 3, date: "Date3", total: "$120"},
                {id: 4, date: "Date4", total: "$70"},
                {id: 5, date: "Date5", total: "$35"},
            ]
        }
    }

    render() {
        const {orders} = this.state;

        return (
            <Container>
                <h1>Orders List</h1>
                <Table hover responsive>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Date</th>
                        <th>Total</th>
                        <th>Delete</th>
                    </tr>
                    </thead>
                    <tbody>
                    {orders.map(({id, date, total}) => (
                        <tr>
                            <th scope="row">{id}</th>
                            <td>{date}</td>
                            <td>{total}</td>
                            <td>
                                <Button color="danger" className="remove-btn">
                                    {/*<span className="glyphicon glyphicon-exclamation-sign"/>*/}

                                </Button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </Table>
            </Container>
        );
    }
}

export default OrderTable;