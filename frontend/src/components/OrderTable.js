import React from 'react';
import {Table, Container, Button} from "reactstrap";
import {connect} from 'react-redux';
import {getOrders, deleteOrder} from "../actions/orderActions";
import PropTypes from 'prop-types';

class OrderTable extends React.Component {
    // constructor(props) {
    //     super(props);
    //
    //     this.state = {
    //         orders: [
    //             {id: 1, date: "Date1", total: "$50"},
    //             {id: 2, date: "Date2", total: "$80"},
    //             {id: 3, date: "Date3", total: "$120"},
    //             {id: 4, date: "Date4", total: "$70"},
    //             {id: 5, date: "Date5", total: "$35"},
    //         ]
    //     }
    // }

    componentDidMount() {
        this.props.getOrders();
    }

    render() {
        const {orders} = this.props.orderReducer;

        return (
            <Container>
                <h1>Orders List</h1>
                <Table hover responsive>
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Date</th>
                        <th>Total</th>
                        <th>Options</th>
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
                                    {/*&times;*/}
                                    Del
                                </Button>
                                &emsp;
                                <Button color="warning" className="remove-btn">
                                    Edit
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

OrderTable.propTypes = {
    getOrders: PropTypes.func.isRequired,
    deleteOrder: PropTypes.func.isRequired,
    orderReducer: PropTypes.object.isRequired
};

const mapStateToProps = (state) => ({
    orderReducer: state.orderReducer
});

export default connect(
    mapStateToProps,
    {getOrders, deleteOrder})(OrderTable);