import React, {useEffect, useState} from "react";
import {usePurchaseOrderTable} from "../PurchaseOrder/usePurchaseOrderTable";
import axios from "axios";
import {useLocation} from "react-router-dom";
function useQuery() {
    const {search} = useLocation();

    return React.useMemo(() => new URLSearchParams(search), [search]);
}
export const useSalesOrderDetails=()=>{
    const query = useQuery();
    const id = query.get("id");
    const [loading, setLoading] = useState(false);
    const [edit, setEdit] = useState(false);
    const [order, setOrder] = useState({
        id: '',
        name: '',
        orderDate: '',
        shippingDate: '',
        totalCost: 0
    });

    useEffect(async () => {
        await axios.get("http://localhost:9000/inv/sales-orders",
            {params: {id: id}})
            .then(response => setOrder(response.data.data))
            .catch(error => console.log(error));
        setLoading(false);
    }, [loading]);

    const handleOrderEdit = () => {
        setEdit(true);
    };
    const handleOrderSave =async () => {
        await axios.put("http://localhost:9000/inv/sales-order",order)
            .catch(error => console.log(error))
            .finally(() => {
                setLoading(false)
                setEdit(false)});
    };

    const handleOrderEditCancel = () => {
        setEdit(false);
        setLoading(true);
    };

    const handleChange = (e) => {
        const {id, value} = e.target;
        setOrder(prevState => ({
            ...prevState,
            [id]: value
        }));
    };

    const handleCompleteOrder = async () => {
        setLoading(true);
        await axios.put(`http://localhost:9000/inv/sales-order-complete?id=${parseInt(id)}`)
            .catch(error => console.log(error))
            .finally(() => {
                setLoading(false)
                setEdit(false)
            });

    }
    const handleCancelOrder = async () => {
        setLoading(true);
        await axios.put(`http://localhost:9000/inv/sales-order-cancel?id=${parseInt(id)}`)
            .catch(error => console.log(error))
            .finally(() => {
                setLoading(false)
                setEdit(false)
            });

    };


    return{
        id,
        loading,
        edit,
        order,
        handleOrderSave,
        handleOrderEdit,
        handleOrderEditCancel,
        handleChange,
        handleCompleteOrder,
        handleCancelOrder
    }
}